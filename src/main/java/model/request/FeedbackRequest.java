package model.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackRequest {
	private Integer id;
    private String idShareholder;
    private String content;
    private Timestamp timeFeedback;
}
