package util;

public class InputSanitizer {

    public static String clean(String input) {
        if (input == null) return null;

        // Remove script tags and dangerous characters
        return input
            .replaceAll("(?i)<script.*?>.*?</script>", "") // remove script tags fully
            .replaceAll("[<>]", ""); // remove < >
    }
}
