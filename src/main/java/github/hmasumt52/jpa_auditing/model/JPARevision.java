package github.hmasumt52.jpa_auditing.model;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import github.hmasumt52.jpa_auditing.util.JPARevisionListener;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="revinfo")
@RevisionEntity(JPARevisionListener.class)
@Data
public class JPARevision implements Serializable {

  private static final long serialVersionUID = 8530213963961662300L;

	@Id
	@GeneratedValue
	@RevisionNumber
	private Long id;

  @RevisionTimestamp
  private Instant timestamp;

  private String modifer;
}
