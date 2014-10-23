package model;

public enum MenuOption {

   SIGN_IN( 1 ),
   SIGN_UP( 2 ),
   SIGN_OUT( 3 ),
   END( 4 );

   private final int value;

   MenuOption( int valueOption ) {
      value = valueOption;
   }

   public int getValue() {
      return value;
   }

}