package com.mds.springboot2.notes

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by ronan on 06/11/17.
 */
@Entity
data class Note(@Id @GeneratedValue var id: Long? = null,
                var text: String? = null,
                var user: String? = null)