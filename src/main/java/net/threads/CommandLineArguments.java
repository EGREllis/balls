package net.threads;

import net.threads.model.Bounds;

public class CommandLineArguments {
    private static final long DEFAULT_TICK_COUNT = 500L;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private static final String CLI_WIDTH_KEY = "-w";
    private static final String CLI_HEIGHT_KEY = "-h";
    private static final String CLI_TICK_KEY = "-t";

    private final Bounds bounds;
    private final long tickCount;

    public CommandLineArguments(Bounds bounds, long tickCount) {
        this.bounds = bounds;
        this.tickCount = tickCount;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public long getTickCount() {
        return tickCount;
    }

    public static CommandLineArguments parseArgs(String[] args) {
        int widthIndex = indexOf(args, CLI_WIDTH_KEY);
        int heightIndex = indexOf(args, CLI_HEIGHT_KEY);
        int milliPerTickIndex = indexOf(args, CLI_TICK_KEY);

        int width = -1;
        int height = -1;
        long milli;
        if (widthIndex < 0 && heightIndex < 0) {
            width = DEFAULT_WIDTH;
            height = DEFAULT_HEIGHT;
        } else if (widthIndex >= 0 && heightIndex >= 0) {
            width = Integer.parseInt(args[widthIndex+1]);
            height = Integer.parseInt(args[heightIndex+1]);
        } else {
            throw new IllegalStateException(String.format("Please specify both height (%1$s) and width (%2$s) or neither [you specified (%1$s %3$d %2$s $4$d)].", CLI_HEIGHT_KEY, CLI_WIDTH_KEY, height, width));
        }
        if (milliPerTickIndex < 0) {
            milli = DEFAULT_TICK_COUNT;
        } else {
            milli = Long.parseLong(args[milliPerTickIndex+1]);
        }
        return new CommandLineArguments(new Bounds(height, width), milli);
    }

    private static int indexOf(String []args, String key) {
        int index = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(key)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
