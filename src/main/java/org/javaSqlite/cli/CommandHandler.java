package org.javaSqlite.cli;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class CommandHandler {

    Terminal terminal;

    CommandHandler(Terminal terminal) {
        this.terminal = terminal;
    }

    public void handle(String command) {
        if(command.startsWith("!")) {
            terminal.writer().println("its system command");
        } else if(command.startsWith("select")) {
            terminal.writer().println("its select query");
        } else {
            terminal.writer().println("unknown command");
        }
        terminal.flush();
    }

    public static void main(String[] args) {

        try {
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();

            CommandHandler commandHandler = new CommandHandler(terminal);

            while(true) {
                String command = reader.readLine("java-sqlite> ");
                commandHandler.handle(command);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
