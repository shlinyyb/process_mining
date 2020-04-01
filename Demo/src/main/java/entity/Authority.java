package entity;

public enum Authority {
    PUBLIC_AUTHORITY("public", 1),
    PRIVATE_AUTHORITY("private",2),
    PROTECTED_AUTHORITY("protected", 3),
    DEFAULT_AUTHORITY("default", 4);

    public String authority;
    public int statusCode;

    Authority(String s, int i) {
        this.authority = s;
        this.statusCode = i;
    }
}
