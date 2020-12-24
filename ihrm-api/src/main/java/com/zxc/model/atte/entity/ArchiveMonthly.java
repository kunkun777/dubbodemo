package com.zxc.model.atte.entity;


import com.zxc.model.atte.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "atte_archive_monthly")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveMonthly extends BaseEntity implements Serializable {

  @Id
  private String id;
  private String companyId;
  private String departmentId;

  private String archiveYear;
  private String archiveMonth;
  private Integer totalPeopleNum;

  private Integer fullAttePeopleNum;
  private Integer isArchived;
}
