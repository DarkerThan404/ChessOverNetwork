package Game;

public class CoordinateConvertor {
    private static String horizontal = "abcdefgh";
    public static Integer[] StringToIntCoord(String pos){
        assert (pos.length() == 2);
        var result = new Integer[2];
        result[0] = horizontal.indexOf(pos.charAt(0)) ;
        result[1] = (8 - Integer.parseInt(pos.substring(1)) % 9) ;
        return result;
    }

    public static String IntToStringCoord(Integer[] coord){
        assert (coord.length == 2);
        var result = "";
        result += horizontal.charAt(coord[0]);
        result += ((8 - coord[1]) % 9);
        return result;
    }
}
