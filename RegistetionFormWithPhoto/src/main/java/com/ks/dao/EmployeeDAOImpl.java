package com.ks.dao;

import java.awt.HeadlessException;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ks.entity.EmployeeEntityPhoto;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public Integer registerWithPhoto(EmployeeEntityPhoto entity) {
		Integer empId = null;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		if (session != null && tx != null) {
			try {
				empId = (Integer) session.save(entity);
				if (empId == null) {
					tx.rollback();
				} else {
					tx.commit();
				}
			} catch (HibernateException e) {
				tx.rollback();
				empId = null;
				e.printStackTrace();
			} catch (Exception e) {
				tx.rollback();
				empId = null;
				e.printStackTrace();
			}
		}
		return empId;
	}

	@Override
	public Integer getTotalEmpNo() {
		List list = null;
		Integer totalEmpRecord = null;
		Session session = factory.openSession();
		if (session != null) {
			try {
				list = session.createCriteria(EmployeeEntityPhoto.class).setProjection(Projections.max("eid")).list();
				totalEmpRecord = Integer.parseInt(list.get(0).toString());

			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalEmpRecord;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntityPhoto> getAllEmpdetails(Integer currentPage) {
		Session session=factory.openSession();
		List<EmployeeEntityPhoto> employeeEntityPhoto=null;
		if(session!=null&&currentPage!=null){
			try{
				employeeEntityPhoto=session.createCriteria(EmployeeEntityPhoto.class)
						.setFirstResult((currentPage-1)*4).setMaxResults(4).list();
			}catch (HibernateException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return employeeEntityPhoto;
	}
	
	public byte[] getPhoto(Integer id){
		String hql="select photoData from EmployeeEntityPhoto where eid=?";
		byte[] image=null;
		Session session=factory.openSession();
		if(session!=null){
			try{
				Query query=session.createQuery(hql);
				query.setParameter(0, id);
				Object object= query.uniqueResult();
				String string=object.toString();
				
				image=string.getBytes();
				
			}catch (ClassCastException e) {
				e.printStackTrace();
			}
			catch (HeadlessException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return image;
	}
	
}
