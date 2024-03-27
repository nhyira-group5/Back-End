package API.nhyira;

public class ProgressoMuscular {
    private String data;
    private double medidaMuscular;

    public ProgressoMuscular(String data, double medidaMuscular) {
        this.data = data;
        this.medidaMuscular = medidaMuscular;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getMedidaMuscular() {
        return medidaMuscular;
    }

    public void setMedidaMuscular(double medidaMuscular) {
        this.medidaMuscular = medidaMuscular;
    }
}
