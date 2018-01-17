package com.example.cmunayll.masterdetail.otto;

/**
 * Created by cmunayll on 17/01/2018.
 */

public class EventosBus {

    public static class FavoriteMainMessage {
        private String mensaje;

        public FavoriteMainMessage(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }
    }

    public static class MainFavoriteMessage {
        private String mensaje;

        public MainFavoriteMessage(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

    }

}
