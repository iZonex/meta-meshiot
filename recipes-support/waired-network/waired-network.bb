DESCRIPTION = "Waired Networking Configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://waired.network \
    "
S = "${WORKDIR}"

inherit allarch systemd

PACKAGECONFIG_append_pn_systemd = " resolved networkd"

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}/systemd/network/
        install -c -m 0644 ${WORKDIR}/waired.network ${D}${sysconfdir}/systemd/network/
    fi
}