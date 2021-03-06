package model;

public enum MenuOption {

   SIGN_IN( 1 ),
   SIGN_UP( 2 ),
   END( 3 );

   private final int value;

   MenuOption( int valueOption ) {
      value = valueOption;
   }

   public int getValue() {
      return value;
   }

}