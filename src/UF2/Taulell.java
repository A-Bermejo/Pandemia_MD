package UF2;

public class Taulell {

    private int files;
    private int columnes;
    private float[][] taulell;

    public Taulell() {
        this.files = 0;
        this.columnes = 0;
        this.taulell = new float[files][columnes];
    }

    public Taulell(int f, int c) {
        this.files = f;
        this.columnes = c;
        this.taulell = new float[files][columnes];
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public int getColumnes() {
        return columnes;
    }

    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }

    public float[][] getTaulell() {
        return taulell;
    }

    public void setTaulell(float[][] taulell) {
        this.taulell = taulell;
    }
}
