public abstract class Shape {
    private final String id;
    private Renderer renderer;

    protected Shape(String id, Renderer renderer) {
        this.id = id;
        this.renderer = renderer;
    }

    public String getId() {
        return id;
    }

    protected Renderer getRenderer() {
        return renderer;
    }

    public void setImplementation(Renderer renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("renderer must not be null");
        }
        this.renderer = renderer;
    }

    public abstract String execute();
}