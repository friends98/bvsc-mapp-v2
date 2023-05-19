package dao;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.MULTIPART_FORM_DATA)
public interface MeetingDao<T> extends CommonDaoUpdate<T>{
	Optional<T> getById(Integer id);
	
	public List<T> getByIdCompany(String idCompany);
	
//	ResponseEntity<String> uploadImage(MultipartFile imageFile);
}
