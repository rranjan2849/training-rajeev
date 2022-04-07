package com.ps.ccf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.ps.cff.entity.Account;
import com.ps.cff.entity.CFFEntryType;
import com.ps.cff.entity.Forecast;
import com.ps.cff.entity.ForecastTransaction;
import com.ps.cff.entity.RoleMaster;
import com.ps.cff.entity.Trasaction;
import com.ps.cff.entity.User;
import com.ps.cff.repositories.ForecastRepository;
import com.ps.cff.repositories.ForecastTransactionRepository;
import com.ps.cff.repositories.UserRepository;
import com.ps.cff.service.ForecastServiceImpl;
import com.ps.cff.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories("com.ps.cff.repositories")
public class CashFlowForcastApplicationTests {

	@Autowired
	private ForecastServiceImpl service;

	@MockBean
	private ForecastTransactionRepository repository;

	@Autowired
	private UserServiceImpl userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ForecastRepository forecastRepository;

	@Test
	public void getUserAllUser() {
		RoleMaster roleMaster=new RoleMaster(1L, "ADMIN");
		when(userRepository.findAll()).thenReturn(Stream.of(new User(1L, "Rajeev", "rajeev@gmail.com", "rajeev@2849",roleMaster)).collect(Collectors.toList()));
		assertEquals(1, userService.getAllUsers().size());
	}
	

	@Test
	public void createUser() {
		RoleMaster roleMaster=new RoleMaster(1L, "ADMIN");
		User user=new User(1L, "Rakesh", "rakesh@gmail.com", "rajeev@2849", roleMaster);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
	}

	@Test
	public void deleteUser() {
		RoleMaster roleMaster=new RoleMaster(1L, "ADMIN");
		User user=new User(1L, "Rakesh", "rakesh@gmail.com", "rajeev@2849", roleMaster);
		when(userRepository.deleteByEmail(user.getEmail())).thenReturn(user);
		assertEquals(user, userService.deleteUser(user.getEmail()));
	}


	@SuppressWarnings("deprecation")
	@Test
	public void getForcastByHorizonPerod() throws Exception{
		Forecast forecast=new Forecast(1L,"frcts_1","EMI","1W",new Timestamp(2022, 3, 14, 0, 0, 0 ,0),new Timestamp(2022, 3, 20, 0, 0, 0 ,0),"PUBLIC");
		Account account=new Account(1L,"Rajeev",31162172727272L,"Saving");
		Trasaction trasaction=new Trasaction(1L, account, CFFEntryType.RECEIVABLE, "Salary", 60000L,new Timestamp(2022, 3, 15, 0, 0, 0 ,0));
		when(repository.findByForecastId(forecast)).thenReturn(Stream.of(new ForecastTransaction(1L, forecast, trasaction)).collect(Collectors.toList()));
		assertEquals(1, service.getForcastByHorizonPeriod(forecast).size());
	}


	@SuppressWarnings("deprecation")
	@Test
	public void deleteForecastByid()throws Exception{
		Forecast forecast=new Forecast(1L,"frcts_1","EMI","1W",new Timestamp(2022, 3, 14, 0, 0, 0 ,0),new Timestamp(2022, 3, 20, 0, 0, 0 ,0),"PUBLIC");
		Integer i=0;
		boolean responce=false;
		when(repository.deleteByForecastId(1L, forecast.getId())).thenReturn(i);
		if(i==1)
			responce=true;
		assertEquals(responce, service.deleteForcastById(1,forecast.getId()));
		
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void UpdateForecastByid()throws Exception{
		Forecast forecast=new Forecast(1L,"frcts_1","EMI","1W",new Timestamp(2022, 3, 14, 0, 0, 0 ,0),new Timestamp(2022, 3, 20, 0, 0, 0 ,0),"PUBLIC");
		when(forecastRepository.save(forecast)).thenReturn(forecast);
		assertEquals(1, forecast.getId());
	}
	
	@Test
	public void createForcast()throws Exception{
		
	}
	
}