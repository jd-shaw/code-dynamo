package com.shaw.mysql.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

public class BaseRepositoryFactoryBean<T extends SimpleJpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	public BaseRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
		return new BaseRepositoryFactory(em);
	}

	/**
	 * 用内部类完成工厂
	 * @param <T>
	 * @param <I>
	 */
	private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager em;

		public BaseRepositoryFactory(EntityManager em) {
			super(em);
			this.em = em;
		}

		/**
		 * 设置=实现类是BaseRepositoryImpl
		 */
		@Override
		protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information,
				EntityManager entityManager) {
			JpaEntityInformation<?, Serializable> entityInformation = this
					.getEntityInformation(information.getDomainType());
			Object repository = this.getTargetRepositoryViaReflection(information,
					new Object[] { entityInformation, entityManager });
			Assert.isInstanceOf(AbstractEntityDao.class, repository);
			return (JpaRepositoryImplementation) repository;
		}

		/**
		 * 设置自定义实现类class
		 */
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return AbstractEntityDao.class;
		}
	}
}
