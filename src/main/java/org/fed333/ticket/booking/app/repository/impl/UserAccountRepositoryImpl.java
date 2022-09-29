package org.fed333.ticket.booking.app.repository.impl;

import org.fed333.ticket.booking.app.model.UserAccount;
import org.fed333.ticket.booking.app.repository.UserAccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountRepositoryImpl extends AbstractHibernateDao<UserAccount, Long>  {

}
