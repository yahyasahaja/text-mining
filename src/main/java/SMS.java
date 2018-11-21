public class SMS {
    private String body;
    private String label;

    public SMS(String body, String label) {
        this.body = body;
        this.label = label;
    }

    public String getBody() {
        return body;
    }

    public String getLabel() {
        return label;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
