import sys
import os
from Querier import Querier
import HTAnalyzer as HTA
import FileManagement as FM
import UserAnalyzer as UA

def newQuery():
    while 1:
        os.system('cls')
        #op = input('Tipo de consulta?\n1) Por HT\n2) Por Usuario\n')
        op = raw_input('Tipo de consulta?\n1) Por HT\n2) Por Usuario\n')
        querierObj = Querier()
        os.system('cls')

        if op == '1':
            print ('---------------------------------------------')
            print ('\tConsulta por HT')
            print ('---------------------------------------------')
            #HT = input('Ingresa el HT a buscar, sin el \"#\"')
            #since = input('A partir de que fecha? (aaaa-mm-dd)')
            #until = input('Hasta que fecha? (aaaa-mm-dd)')
            #items = input('Numero de tweets a procesar:')
            term = raw_input('Ingresa el HT a buscar, sin el \"#\"\n')
            month_s = 15
            month_u = 15
            ent = '00 00'
            while (month_s >= 13 or month_s <= 0) or len(ent) != 5 or (day_s <= 0 or day_s >= 32):
                ent = raw_input('Ingresa la fecha de INICIO (dia y mes) separada por espacios Ej:(02 02)\n')
                if len(ent) == 5:
                    if (int(ent[3:5]) >= 13 or int(ent[3:5]) <= 0) or (int(ent[0:2]) <= 0 or int(ent[0:2]) >= 32):
                        print 'Ingresa una fecha correcta\n'
                        ent = '00 00'
                else:
                    print 'Ingresa una fecha correcta\n'
                    ent = '00 00'
                day_s = int(ent[0:2])
                month_s = int(ent[3:5])
            ent = '00 00'
            while (month_u >= 13 or month_u <= 0) or len(ent) != 5 or (day_u <= 0 or day_u >= 32):
                ent = raw_input('Ingresa la fecha de FIN (dia y mes) separada por espacios Ej:(02 10)\n')
                if len(ent) == 5:
                    if (int(ent[3:5]) >= 13 or int(ent[3:5]) <= 0) or (int(ent[0:2]) <= 0 or int(ent[0:2]) >= 32):
                        print 'Ingresa una fecha correcta\n'
                        ent = '00 00'
                else:
                    print 'Ingresa una fecha correcta\n'
                    ent = '00 00'
                day_u = int(ent[0:2])
                month_u = int(ent[3:5])
            ans = 'x'
            while ans != 's' and ans != 'n':
                ans = raw_input('Deseas seleccionar el idioma? R= s/n\n')
            if ans == 's':
                language = raw_input('Ingresa el idioma Ej: en = ingles, es = espanol\n')
            else:
                language = None
            ans = 'x'
            while (ans != 's') and (ans != 'n'):
                ans = raw_input('Deseas descargar todos los tweets disponibles en esa fecha? R: s/n\n')
            if ans == 'n':
                items = raw_input('Numero de tweets a procesar:\n')
                items = int(items)
            else:
                items = None

            if month_s <= 9: month_s = '0'+ str(month_s);
            else: month_s = str(month_s);
            if month_u <= 9: month_u = '0'+ str(month_u);
            else: month_u = str(month_u);
            if day_s <= 9: day_s = '0'+ str(day_s);
            else: day_s = str(day_s);
            if day_u <= 9: day_u = '0'+ str(day_u);
            else: day_u = str(day_u);

            since = '2017' + '-' + month_s + '-' + day_s
            until = '2017' + '-' + month_u + '-' + day_u

            print('Realizando consulta...')

            tweets, queryPath = querierObj.queryByHashtag(term, since, until, resultType="recent", items=items, lang=language, savedEnabled=True)

            ht2ht = HTA.getHTtoHT(tweets)
            u2ht = UA.getUserToHT(tweets)
            u2u = UA.getUserToUser(tweets)

            FM.saveGraphCSV(path=queryPath, name=term, graphType="u2ht", buffer=u2ht, type="directed")
            FM.saveGraphCSV(path=queryPath, name=term, graphType="ht2ht", buffer=ht2ht, type="undirected")
            FM.saveGraphCSV(path=queryPath, name=term, graphType="u2u", buffer=u2u, type="directed")
            FM.saveWordcloudFile(path=queryPath, name=term, buffer=tweets)

        elif op == '2':
            print ('---------------------------------------------')
            print ('\tConsulta por Usuario')
            print ('---------------------------------------------')
            #username = input('Ingresa el username a buscar:')
            #since = input('A partir de que fecha? (aaaa-mm-dd)')
            #until = input('Hasta que fecha? (aaaa-mm-dd)')
            term = raw_input('Ingresa el username a buscar:')
            since = raw_input('A partir de que fecha? (aaaa-mm-dd)')
            until = raw_input('Hasta que fecha? (aaaa-mm-dd)')
            items = raw_input('Numero de tweets a procesar:\n')

            print('Realizando consulta...')
            tweets, queryPath = querierObj.queryByUser(term, since, until, resultType="recent", items=int(items), savedEnabled=True)

            ht2ht = HTA.getHTtoHT(tweets)
            u2ht = UA.getUserToHT(tweets)
            u2u = UA.getUserToUser(tweets)

            FM.saveGraphCSV(path=queryPath, name=term, graphType="u2ht", buffer=u2ht, type="directed")
            FM.saveGraphCSV(path=queryPath, name=term, graphType="ht2ht", buffer=ht2ht, type="undirected")
            FM.saveGraphCSV(path=queryPath, name=term, graphType="u2u", buffer=u2u, type="directed")
            FM.saveWordcloudFile(path=queryPath, name=term, buffer=tweets)

        #input('Presiona cualquier tecla para continuar...')
        raw_input('Presiona cualquier tecla para continuar...')

        break

def showSavedQueries():
    print('Caracteristica en desarrollo')
    #input('Presiona cualquier tecla para continuar...')
    raw_input('Presiona cualquier tecla para continuar...')
    os.system('cls')


def menu():
    print ('---------------------------------------------')
    print ('Bienvenido! Selecciona una opcion:')
    print ('---------------------------------------------\n')
    print ('1) Realizar una consulta')
    print ('2) Listar consultas almacenadas')
    print('3) Salir')
    op = raw_input()

    if op == '1':
        newQuery()
    elif op == '2':
        showSavedQueries()
    elif op == '3':
        os.system('cls')
        print('\n\nSaliendo...')
        sys.exit()

def main():
    while 1:
        menu()

if __name__ == "__main__":
    main()
