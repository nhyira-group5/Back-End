package API.nhyira.apivitalis.Utils;

import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

public class ErrorMessageHelper {
    public static void assertContainsErrorMessages(MvcResult result, List<String> expectedErrorMessages) {

        MethodArgumentNotValidException resolvedException =
                (MethodArgumentNotValidException) result.getResolvedException();

        Assertions.assertNotNull(resolvedException, "Expected MethodArgumentNotValidException to be thrown, but it was not.");

        List<String> actualErrorMessages = resolvedException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> missingErrorMessages = expectedErrorMessages.stream()
                .filter(expected -> !actualErrorMessages.contains(expected))
                .collect(Collectors.toList());

        if (!missingErrorMessages.isEmpty()) {
            String missingErrorsFormatted = String.join(", ", missingErrorMessages);
            String actualErrorsFormatted = String.join(", ", actualErrorMessages);
            Assertions.fail(String.format(
                    "The following expected error messages were not found: [%s]\nActual error messages: [%s]",
                    missingErrorsFormatted, actualErrorsFormatted
            ));
        }
    }
}
