package logic;

public class Data {
    private String tree;
    private double harvest;

    public Data(String tree, double harvest) {
        this.tree = tree;
        this.harvest = harvest;
    }

    /**
     * Restore data from table cell value
     *
     * @param data Value in JTable
     */
    public Data(String data) {
        String[] split = data.split("-");
        this.tree = split[0].trim();
        this.harvest = Double.parseDouble(split[1].trim());
    }

    public String getTree() {
        return tree;
    }

    public double getHarvest() {
        return harvest;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", tree, harvest);
    }

    public static void main(String[] args) {
        System.out.println(new Data("Яблоко", 1.123));
        System.out.println(new Data("Яблоко - 2.2345"));
        System.out.println(new Data("Яблоко - 123456789.123456789"));
    }
}
