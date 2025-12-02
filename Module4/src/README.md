# Calculator – MVC Model Class
### Evaluation of GitHub Copilot

| Benefits                                              | Drawbacks                                              |
|-------------------------------------------------------|--------------------------------------------------------|
| Extremely fast for boilerplate & validation code      | Sometimes suggests outdated or verbose patterns        |
| Understands natural-language comments perfectly       | Requires careful review — not always 100 % correct     |
| Excellent at generating Javadoc & exception handling  | Can make you lazy if over-relied on                    |
| Huge productivity boost (3–5× faster coding)          | May reduce deep understanding for complete beginners   |
| Great teaching tool — shows clean, idiomatic Java     | Occasionally hallucinates wrong method names           |

**Personal conclusion**  
GitHub Copilot is one of the best programming tools I have ever used.  
In this assignment it wrote **~85 % of the code correctly on the first suggestion**. I only had to adjust formatting and add the demonstration in `main()`.  
Highly recommended for experienced developers — it removes tedium and lets you focus on design.  
Very helpful for beginners too, but only when used with understanding and review.

**Score: 9.5 / 10**

## Features
- Add positive integers
- Prevent negative numbers (throws exception)
- Reset to zero
- Get current value

## Source Code

```java
public class Calculator {
    private int value;

    public Calculator() {
        this.value = 0;
    }

    /** Resets the calculator to zero */
    public void reset() {
        this.value = 0;
    }

    /** Adds a positive integer. Throws exception if negative. */
    public void add(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot add negative number: " + number);
        }
        this.value += number;
    }

    /** Returns current value */
    public int getValue() {
        return this.value;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.add(10);
        calc.add(20);
        calc.add(30);
        System.out.println("Current value: " + calc.getValue()); // 60

        try {
            calc.add(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        calc.reset();
        System.out.println("After reset: " + calc.getValue()); // 0
    }
}