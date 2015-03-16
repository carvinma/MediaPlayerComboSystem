package com.hysd.service;

import java.util.Map;

import com.hysd.domain.Client;
import com.hysd.domain.page.Page;

public interface ClientService {
	public void save(Client entity);

	public void update(Client entity);

	public Client findById(Long id);

	public void delete(Client entity);

	Page<Client> list(Page<Client> page, Map<String, String> map);
	 
}
