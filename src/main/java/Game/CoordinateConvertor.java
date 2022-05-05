package Game;

public class CoordinateConvertor {
    private static String horizontal = "abcdefgh";
    public static Integer[] StringToIntCoord(String pos){
        var result = new Integer[2];
        result[0] = horizontal.indexOf(pos.charAt(0));
        result[1] = Integer.parseInt(pos.substring(1));
        return result;
    }

    public static String IntToStringCoord(Integer[] coord){
        var result = "";
        result += coord[0].toString();
        result += coord[1].toString();
        return result;
    }
}
