package com.ks.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ks.entity.EmployeeEntity;
import com.ks.entity.EmployeeEntityPhoto;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public Integer saveEmployee(EmployeeEntity entity) {
		Integer empId = null;
		if (factory != null) {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				if (session != null && tx != null) {
					empId = (Integer) session.save(entity);
					if (empId == null) {
						tx.rollback();
					} else {
						tx.commit();
					}
				}
			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				try {
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return empId;
	}

	@Override
	public Integer getPageNo() {
		String hql = "select count(*) from EmployeeEntity";
		List totalNoOfRecords = null;
		Session session = factory.openSession();
		if (session != null) {
			Query query = session.createQuery(hql);
			totalNoOfRecords = query.list();
		}
		// SString value = ""+(totalNoOfRecords.get(0));
		return Integer.parseInt("" + (totalNoOfRecords.get(0)));
	}

	@Override
	public List<EmployeeEntity> getEmployeeDetails(Integer currentPage) {
		List<EmployeeEntity> list = null;
		Session session = factory.openSession();
		if (session != null) {
			Criteria criteria = session.createCriteria(EmployeeEntity.class);
			criteria.setFirstResult((currentPage - 1) * 5);
			criteria.setMaxResults(5);
			list = criteria.list();
		}
		return list;
	}

	@Override
	public Integer update(EmployeeEntity entity) {
		Integer empId = 0;
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		if (session != null && transaction != null) {
			try {
				session.update(entity);
				transaction.commit();
				empId = entity.getEid();
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
				empId = 0;

			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
				empId = 0;
			}
		}
		return empId;
	}

	@Override
	public Integer delete(String eid) {
		Integer empId = 0;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		if (tx != null && session != null) {
			try {
				EmployeeEntity employeeEntity = new EmployeeEntity();
				employeeEntity.setEid(Integer.parseInt(eid));
				session.delete(employeeEntity);
				empId = employeeEntity.getEid();
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			}
		}
		return empId;
	}

	@Override
	public Integer registerWithPhoto(EmployeeEntityPhoto entity) {
		Integer empId=null;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		if (session != null && tx != null) {
			try {
				empId=(Integer) session.save(entity);
				if(empId==null){
					tx.rollback();
				}else{
					tx.commit();
				}
			} catch (HibernateException e) {
				tx.rollback();
				empId=null;
				e.printStackTrace();
			} catch (Exception e) {
				tx.rollback();
				empId=null;
				e.printStackTrace();
			}
		}
		return empId;
	}

}
