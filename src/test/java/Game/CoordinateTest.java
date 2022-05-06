package Game;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CoordinateTest  {
    @Test
    public void CoordinatTestIntToString1(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{0,7});
        Assert.assertEquals("a1", result);
    }
    @Test
    public void CoordinatTestIntToString2(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{7,0});
        Assert.assertEquals("h8", result);
    }

    @Test
    public void CoordinatTestIntToString3(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{4,4});
        Assert.assertEquals("e4", result);
    }

    @Test
    public void CoordinatTestIntToString4(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{4,3});
        Assert.assertEquals("e5", result);
    }

    @Test
    public void CoordinatTestIntToString5(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{5,3});
        Assert.assertEquals("f5", result);
    }

    @Test
    public void CoordinatTestIntToString6(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{5,4});
        Assert.assertEquals("f4", result);
    }

    @Test
    public void CoordinatTestIntToString7(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{0,3});
        Assert.assertEquals("a5", result);
    }

    @Test
    public void CoordinatTestIntToString8(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{0,4});
        Assert.assertEquals("a4", result);
    }

    @Test
    public void CoordinatTestIntToString9(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{2,6});
        Assert.assertEquals("c2", result);
    }

    @Test
    public void CoordinatTestIntToString10(){

        var result = CoordinateConvertor.IntToStringCoord(new Integer[]{2,2});
        Assert.assertEquals("c6", result);
    }
    @Test
    public void CoordinatTestStringToInt1(){

        var result = CoordinateConvertor.StringToIntCoord("h8");
        Integer expectedX = 7;
        Integer expectedY = 0;
        Assert.assertEquals(expectedX, result[0]);
        Assert.assertEquals(expectedY, result[1]);
    }

    @Test
    public void CoordinatTestStringToInt2(){

        var result = CoordinateConvertor.StringToIntCoord("a1");
        Integer expectedX = 0;
        Integer expectedY = 7;
        Assert.assertEquals(expectedX, result[0]);
        Assert.assertEquals(expectedY, result[1]);
    }

    @Test
    public void CoordinatTestStringToInt3(){

        var result = CoordinateConvertor.StringToIntCoord("c5");
        Integer expectedX = 2;
        Integer expectedY = 3;
        Assert.assertEquals(expectedX, result[0]);
        Assert.assertEquals(expectedY, result[1]);
    }

    @Test
    public void CoordinatTestStringToInt4(){

        var result = CoordinateConvertor.StringToIntCoord("g6");
        Integer expectedX = 6;
        Integer expectedY = 2;
        Assert.assertEquals(expectedX, result[0]);
        Assert.assertEquals(expectedY, result[1]);
    }
}
