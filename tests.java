package Sprint0;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.AssertionFailedError;

public class tests {
   int arr1[] = {1,2,3,4,5};
   int maxNum = Calculator.findMax(arr1);
   String myString = "Apple";

   // Test if max variable set.
   @Test
   public void isMax() throws Exception {
      assertEquals(5, maxNum);
   }

   // Test if word can be spelled backwards.
   @Test
   public void isReversed() throws Exception {
      assertEquals("sananaB", "elppA");
   }
}
