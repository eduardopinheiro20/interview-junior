package br.com.brainweb.interview.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
/**
 *
 * @author eduardo-pinheiro
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class DeleteHeroRequest {

//    @NotBlank(message = "message.id.mandatory")
//    @Length(min = 1, max = 255, message = "message.id.length")
    private UUID id;

}