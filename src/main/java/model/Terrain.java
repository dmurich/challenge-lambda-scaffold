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
}
