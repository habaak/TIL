package second.sample.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public interface SampleService {
	
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;
}