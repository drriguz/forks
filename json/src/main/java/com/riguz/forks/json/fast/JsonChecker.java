package com.riguz.forks.json.fast;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static com.riguz.forks.json.fast.Mapping.ASCII_CLASS;
import static com.riguz.forks.json.fast.Mapping.__;
import static com.riguz.forks.json.fast.Mapping.state_transition_table;
import static com.riguz.forks.json.fast.States.*;
import static com.riguz.forks.json.fast.Tokens.*;

public class JsonChecker {
    protected final int maxDepth;
    protected int state = GO;
    protected boolean valid = true;
    protected int top = -1;
    protected final Mode[] stack;

    private JsonChecker(int maxDepth) {
        this.maxDepth = maxDepth;
        this.stack = new Mode[maxDepth];
        push(Mode.MODE_DONE);
    }

    public static boolean check(String json, int maxDepth) throws IOException {
        return check(new StringReader(json), maxDepth);
    }

    public static boolean check(Reader reader, int maxDepth) throws IOException {
        JsonChecker checker = new JsonChecker(maxDepth);
        int next;
        int i = 0;
        while ((next = reader.read()) != -1) {
            i++;
            if (!checker.check_char(next)) {
                System.out.println("Failed:" + (char) next + " at:" + i);
                return false;
            }
        }
        if (!checker.done())
            return false;
        return true;

    }

    protected boolean push(Mode mode) {
        this.top++;
        if (this.top >= this.maxDepth)
            return false;
        this.stack[this.top] = mode;
        return true;
    }

    protected boolean pop(Mode mode) {
        if (this.top < 0 || this.stack[this.top] != mode)
            return false;
        this.top--;
        return true;
    }

    protected boolean reject() {
        this.valid = false;
        return false;
    }

    protected boolean check_char(int next) {
        int nextClass, nextState;
        if (!this.valid)
            return false;
        if (next < 0)
            return reject();
        if (next >= 128)
            nextClass = C_ETC;
        else {
            nextClass = ASCII_CLASS[next];
            if (nextClass <= __)
                return reject();
        }

        nextState = state_transition_table[this.state][nextClass];
        if (nextState >= 0) {
            this.state = nextState;
        } else {
            switch (nextState) {
                case -9: // empty }
                    if (!pop(Mode.MODE_KEY))
                        return reject();
                    state = OK;
                    break;
                case -8: // }
                    if (!pop(Mode.MODE_OBJECT))
                        return reject();
                    state = OK;
                    break;
                case -7: // ]
                    if (!pop(Mode.MODE_ARRAY))
                        return reject();
                    state = OK;
                    break;
                case -6: // {
                    if (!push(Mode.MODE_KEY))
                        return reject();
                    state = OB;
                    break;
                case -5: // [
                    if (!push(Mode.MODE_ARRAY))
                        return reject();
                    state = AR;
                    break;
                case -4: // "
                    switch (stack[top]) {
                        case MODE_KEY:
                            state = CO;
                            break;
                        case MODE_ARRAY:
                        case MODE_OBJECT:
                            state = OK;
                            break;
                        default:
                            return reject();
                    }
                    break;
                case -3: // ,
                    switch (stack[top]) {
                        case MODE_OBJECT:
                            if (!pop(Mode.MODE_OBJECT) || !push(Mode.MODE_KEY))
                                return reject();
                            state = KE;
                            break;
                        case MODE_ARRAY:
                            state = VA;
                            break;
                        default:
                            return reject();
                    }
                    break;
                case -2: // :
                    if (!pop(Mode.MODE_KEY) || !push(Mode.MODE_OBJECT))
                        return reject();
                    state = VA;
                    break;
                default:
                    return reject();
            }
        }
        return true;
    }

    public boolean done() {
        if (!valid)
            return false;
        return state == OK && pop(Mode.MODE_DONE);
    }
}
