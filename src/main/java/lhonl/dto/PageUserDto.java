package lhonl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageUserDto {
	
	@Builder.Default
	private int page = 0;
	@Builder.Default
	private int size = 4;
	
	private String sort;
	
}
