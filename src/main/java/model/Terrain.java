package model;

public enum Terrain {
    Highway(70, 'H'),
    Railway(50, 'T'),
    StandardTerrain(100, '_'),
    RailwayLevelCrossing(120, 'X'),
    Dirt(150, '+'),
    TrafficJam(200, '*'),
    Water(800, '~'),
    Mountains(-1, '#');
    public int value;
    public char character;

    Terrain(int value, char character) {
        this.value = value;
        this.character = character;
    }

    Terrain(char character) {
        this.character = character;
    }

    public static Terrain valueOf(char character) {
        switch(character) {
            case 'H':
                return Terrain.Highway;
            case 'T':
                return Terrain.Railway;
            case '_':
                return Terrain.StandardTerrain;
            case 'X':
                return Terrain.RailwayLevelCrossing;
            case '+':
                return Terrain.Dirt;
            case '*':
                return Terrain.TrafficJam;
            case '~':
                return Terrain.Water;
            case '#':
                return Terrain.Mountains;
            default:
//                TODO default cases
                return Terrain.Mountains;
        }
    }
}
