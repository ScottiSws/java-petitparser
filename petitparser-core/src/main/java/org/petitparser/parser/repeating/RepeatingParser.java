package org.petitparser.parser.repeating;

import org.petitparser.parser.Parser;
import org.petitparser.parser.combinators.DelegateParser;

import java.util.Objects;

/**
 * An abstract parser that repeatedly parses between 'min' and 'max' instances of its delegate.
 */
public abstract class RepeatingParser extends DelegateParser {

  public static final int UNBOUNDED = -1;

  protected final int min;
  protected final int max;

  public RepeatingParser(Parser delegate, int min, int max) {
    super(delegate);
    this.min = min;
    this.max = max;
    assert 0 <= min;
    assert max == UNBOUNDED || min <= max;
  }

  @Override
  public String toString() {
    return super.toString() + "[" + min + ".." + (max == UNBOUNDED ? "*" : max) + "]";
  }

  @Override
  public boolean equalsProperties(Parser other) {
    return super.equalsProperties(other) &&
        Objects.equals(min, ((RepeatingParser) other).min) &&
        Objects.equals(max, ((RepeatingParser) other).max);
  }
}
