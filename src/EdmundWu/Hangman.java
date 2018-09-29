package EdmundWu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Hangman {
    private String message;
    private int strikes;
    private List<String> misses;
    private String output;

    Hangman(String word) {
        this.strikes = 0;
        this.message = word.toUpperCase();
        this.misses = new ArrayList<>();
        this.output = String.join("", Collections.nCopies(word.length(), "_"));
    }

    int getStrikes() {
        return this.strikes;
    }

    String getWord() {
        return this.message;
    }

    String getOutput() {
        return this.output;
    }

    private void redraw (String currentGuess) {
        // Flush out stdout, or attempt to
        // https://www.reddit.com/r/java/comments/1uuvqo/clear_terminal_screen_linux/
        System.out.flush();
        System.out.print("\033[H\033[2J");

        // Redraw hangman
        /*
            ________
               |   |
               o   |  Word:
              \|/  |  Guess:
              / \  |  Misses:
                   |
            --------
         */
        System.out.printf("________\n   |   |\n   %s   |  Word: %s\n",
                this.strikes > 0 ? 'o' : ' ',
                output);
        System.out.printf("  %s%s%s  |  Guess: %s\n",
                this.strikes > 2 ? '\\' : ' ',
                this.strikes > 1 ? '|' : ' ',
                this.strikes > 3 ? '/' : ' ',
                currentGuess);
        System.out.printf("  %s %s  |  Misses: %s\n       |\n--------\n",
                this.strikes > 4 ? '/' : ' ',
                this.strikes > 5 ? '\\' : ' ',
                '{' + String.join(", ", this.misses) + '}');
    }

    void test(String letter) {
        if (!this.message.contains(letter) && !this.misses.contains(letter)) {
            this.misses.add(letter);
            this.strikes = this.strikes + 1;
        } else {
            IntStream.range(0, this.output.length()).forEach(i -> {
                if (String.valueOf(this.message.charAt(i)).equals(letter)) {
                    this.output = this.output.substring(0, i) + letter + this.output.substring(i + 1);
                }
            });
        }
        this.redraw(letter);
    }
}