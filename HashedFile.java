public class HashedFile {
    private final String name;
    private final String hash;

    public HashedFile(String name, String hash) {
        this.name = name;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return String.format("%s = %s", name, hash);
    }
}
