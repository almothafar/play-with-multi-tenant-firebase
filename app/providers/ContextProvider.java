package providers;

import play.mvc.Http;

import javax.inject.Provider;

/**
 * Created by rrabata on 2/26/17.
 */
public class ContextProvider implements Provider<Http.Context> {

  @Override public Http.Context get() {
      return Http.Context.current();
  }
}
