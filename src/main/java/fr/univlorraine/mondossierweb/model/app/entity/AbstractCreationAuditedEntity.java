/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package fr.univlorraine.mondossierweb.model.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/**
 * Superclass d'Entité suivie.
 * @author Adrien Colson
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@SuppressWarnings("serial")
public abstract class AbstractCreationAuditedEntity implements Serializable {

	/** Date de création. */
	@CreatedDate
	private LocalDateTime createdDate;

	/** Utilisateur qui a fait la création. */
	@CreatedBy
	@ManyToOne(optional = true)
	@JoinColumn(name = "created_by")
	@NotFound(action = NotFoundAction.IGNORE)
	private Utilisateur createdBy;

}
