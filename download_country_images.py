import urllib2

countries = [
    'china',
    'india',
    'united_states',
    'indonesia',
    'brazil',
    'pakistan',
    'nigeria',
    'bangladesh',
    'russia',
    'japan',
    'mexico',
    'philippines',
    'vietnam',
    'ethiopia',
    'egypt',
    'germany',
    'iran',
    'turkey',
    'democratic_republic_of_the_congo',
    'france',
    'thailand',
    'united_kingdom',
    'italy',
    'south_africa',
    'burma',
    'south_korea',
    'colombia',
    'tanzania',
    'spain',
    'ukraine',
    'argentina',
    'kenya',
    'algeria',
    'poland',
    'sudan',
    'uganda',
    'iraq',
    'canada',
    'morocco',
    'peru',
    'saudi_arabia',
    'uzbekitan',
    'malaysia',
    'venezuela',
    'ghana',
    'nepal',
    'afghanistan',
    'yemen',
    'mozambique',
    'north_korea',
    'angola',
    'australia',
    'taiwan',
    'ivory_coast',
    'syria',
    'madagascar',
    'cameroon',
    'sri_lanka',
    'romania',
    'chile',
    'kazahkstan',
    'burkina_faso',
    'niger',
    'netherlands',
    'ecuador',
    'guatemala',
    'malawi',
    'mali',
    'cambodia',
    'zambia',
    'senegal',
    'chad',
    'zimbabwe',
    'south_sudan',
    'belgium',
    'cuba',
    'rwanda',
    'greece',
    'tunisia',
    'somalia',
    'haiti',
    'guinea',
    'czech_republic',
    'portugal',
    'dominican_republic',
    'bolivia',
    'benin',
    'hungary',
    'sweden',
    'azerbaijan',
    'burundi',
    'belarus',
    'united_arab_emirates',
    'honduras',
    'austria',
    'israel',
    'switzerland',
    'tajikistan',
    'papua_new_guinea',
    'bulgaria'
]

for country in countries:
    url = 'http://mapsof.net/uploads/static-maps/' + country + '_flag_map.png'
    file_name = 'country-images/' + country + '.png'
    try:
        u = urllib2.urlopen(url)
    except urllib2.HTTPError:
        print "Couldn't get image for " + country
        continue
    f = open(file_name, 'wb')
    meta = u.info()
    file_size = int(meta.getheaders("Content-Length")[0])
    print "Downloading: %s Bytes: %s" % (file_name, file_size)

    file_size_dl = 0
    block_sz = 8192
    while True:
        buffer = u.read(block_sz)
        if not buffer:
            break

        file_size_dl += len(buffer)
        f.write(buffer)
        status = r"%10d  [%3.2f%%]" % (file_size_dl, file_size_dl * 100. / file_size)
        status = status + chr(8)*(len(status)+1)
        print status,

    f.close()
