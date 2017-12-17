(function () {
  'use strict'

  angular.module('stoneBoard')
    .factory('authService', function (authConfig, $http, $q, $location, $localStorage, $rootScope) {

      let urlUsuario = authConfig.urlUsuario;
      let urlLogin = authConfig.urlLogin;
      let urlPrivado = authConfig.urlPrivado;
      let urlLogout = authConfig.urlLogout;

      function login(usuario) {
        let deferred = $q.defer();
        let hash = gerarHash(usuario);
        let headerAuth = montarHeader(hash);

        $http({
          url: urlUsuario,
          method: 'GET',
          headers: headerAuth
        }).then(
          function (response) {
            $localStorage.usuarioLogado = response.data;
            $localStorage.headerAuth = montarHeader(hash)['Authorization'];
            $localStorage.webSocketAuth = hash;
            $http.defaults.headers.common.Authorization = $localStorage.headerAuth;
            $rootScope.$broadcast('authLoginSuccess');


            $location.path('/dashboard');

            deferred.resolve(response);
          },

          function (response) {
            deferred.reject(response);
          });

        return deferred.promise;
      };

      function logout() {
        delete $localStorage.usuarioLogado;
        delete $localStorage.headerAuth;
        delete $localStorage.webSocketAuth;
        $http.defaults.headers.common.Authorization = undefined;

        $rootScope.$broadcast('authLogoutSuccess');

        if (urlLogout) {
          $location.path(urlLogout);
        }
      };

      function getUsuario() {
        return $localStorage.usuarioLogado;
      };

      function isAutenticado() {
        return !!getUsuario();
      };

      function possuiPermissao(permissao) {
        return isAutenticado() &&
          getUsuario().Permissoes.find((p) => p.authority === permissao);
      };


      function isAutenticadoPromise() {

        let deferred = $q.defer();

        if (isAutenticado()) {
          deferred.resolve();

        } else {
          $location.path(urlLogin);
          deferred.reject();
        }

        return deferred.promise;
      };

      function possuiPermissaoPromise(permissao) {

        let deferred = $q.defer();

        if (possuiPermissao(permissao)) {
          deferred.resolve();

        } else {
          deferred.reject();
        }

        return deferred.promise;
      };

      function webSocketAuth() {
        return $localStorage.webSocketAuth;
      }

      function gerarHash(usuario) {
        return window.btoa(`${usuario.email}:${usuario.senha}`);
      }

      function montarHeader(hash) {
        return {
          'Authorization': `Basic ${hash}`
        };
      };

      return {
        login: login,
        logout: logout,
        getUsuario: getUsuario,
        possuiPermissao: possuiPermissao,
        isAutenticado: isAutenticado,
        isAutenticadoPromise: isAutenticadoPromise,
        possuiPermissaoPromise: possuiPermissaoPromise,
        webSocketAuth: webSocketAuth
      };
    });

})();
