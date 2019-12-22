package m.s.h.cloudserver.repository

import m.s.h.cloudserver.model.LoginHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoginHistoryRepository : JpaRepository<LoginHistory, Integer> {
}