{
  description = "Development environment in nix for Minecraft 1.21.4";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs";
  outputs = { self, nixpkgs }: 
  let
    system = "x86_64-linux";
    pkgs = import nixpkgs {
      inherit system;
    };
    libs = with pkgs; [
        libpulseaudio
        libGL
        glfw
        openal
        stdenv.cc.cc.lib
      ];
  in {
    devShell.${system} = pkgs.mkShell {
      packages = with pkgs; [
        xorg.libX11
        xorg.libXext
        xorg.libXcursor
        xorg.libXrandr
        xorg.libXxf86vm
        xorg.libXrender
        xorg.libXi
        xorg.libXtst
        libglvnd
        libpulseaudio
        libGL
        openal
        glfw
        stdenv.cc.cc.lib
        udev
        xorg.xrandr
        jdk21 # Java
      ];

      nativeBuildInputs = with pkgs; [
        glfw
        (writeShellApplication {
          name = "runClient";
          text = ''
            #!/usr/bin/env bash
            # shut up shellcheck
            # shellcheck disable=all

            ./gradlew runClient "$@"
          '';
        })
      ];

      buildInputs = libs;
      LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath libs;


      shellHook = ''
        #export LD_LIBRARY_PATH="''${LD_LIBRARY_PATH}''${LD_LIBRARY_PATH:+:}${pkgs.libglvnd}/lib${pkgs.addDriverRunpath.driverLink}/lib"
        echo "Minecraft development environment activated"
      '';
    };
  };
}
