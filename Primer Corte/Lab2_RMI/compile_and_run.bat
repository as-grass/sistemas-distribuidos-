@echo off
echo Compilando archivos RMI...

REM Compilar todos los archivos Java
javac *.java

if %ERRORLEVEL% NEQ 0 (
    echo Error en la compilacion
    pause
    exit /b 1
)

echo Compilacion exitosa!
echo.
echo Para ejecutar el servidor:
echo java SumadorServer
echo.
echo Para ejecutar el cliente (en otra terminal):
echo java SumadorClient
echo.
echo Presiona cualquier tecla para continuar...
pause
