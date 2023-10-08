package tn.esprit.CROTUN.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.DeviceMetadata;

@Repository
public interface DeviceRepository  extends CrudRepository<DeviceMetadata, Long>{
	
	@Query("Select D from DeviceMetadata D where agent_device_ida=?1")
	List<DeviceMetadata> findByAgentId(Long AgentId);
	
	@Query("Select D from DeviceMetadata D where customer_device_idc=?1")
	List<DeviceMetadata> findByCustomerId(Long CustomerId);
	
	@Query("Select D from DeviceMetadata D where manager_device_idm=?1")
	List<DeviceMetadata> findByManagerId(Long ManagerId);
	
	@Query("Select D from DeviceMetadata D where investor_device_id_investor=?1")
	List<DeviceMetadata> findByInvestorId(Long InvestorId);
	
	

}
