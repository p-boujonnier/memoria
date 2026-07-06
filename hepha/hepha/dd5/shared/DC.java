public record DC(
    APIReference dcType,
    int dcValue,
    String successType  // "none" | "half" | "other"
) {}