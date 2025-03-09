package rom41k.Rhythmix.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final T data;

    public static ApiResponse<Map<String, String>> message(String message) {
        return new ApiResponse<>(Map.of("message", message));
    }
}
