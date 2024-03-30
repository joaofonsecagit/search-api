package com.app.documentapi.domain;

import com.app.documentapi.domain.model.Index;

public interface IndexRepository {

  void update(Index index);

  Index get();


}