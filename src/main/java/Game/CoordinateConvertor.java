package Game;

public class CoordinateConvertor {
    private static String horizontal = "abcdefgh";
    private static String reversed = "87654321";

    /**
     * Takes position in string format and converts it to array of integers of size 2
     * @param pos Position to being converted from
     * @return Array of Integers of size 2
     */
    public static Integer[] StringToIntCoord(String pos){
        assert (pos.length() == 2);
        var result = new Integer[2];
        result[0] = horizontal.indexOf(pos.charAt(0)) ;
        result[1] = (8 - Integer.parseInt(pos.substring(1)) % 9) ;
        return result;
    }

    /**
     * Takes position in array format of size 2 and converts it to a string format
     * @param coord Array of Integers of size 2
     * @return Position in string format
     */
    public static String IntToStringCoord(Integer[] coord){

        assert (coord.length == 2);
        var result = "";
        result += horizontal.charAt(coord[0]);
        result += reversed.charAt(coord[1])  ;

        return result;
    }


}
