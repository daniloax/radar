package model;

public enum MainMenu {

   CELL_VIEW( 1 ),
   RADAR_VIEW( 2 ),
   POSITION_UPDATE( 3 ),
   EXIT( 4 );

   private final int value;

   MainMenu( int valueOption ) {
      value = valueOption;
   }

   public int getValue() {
      return value;
   }

}