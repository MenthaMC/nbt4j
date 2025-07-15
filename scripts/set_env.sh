prop() {
  grep "^[[:space:]]*${1}" gradle.properties | cut -d'=' -f2 | sed 's/^[[:space:]]*//; s/\r//'
}

version=$(prop version)
prerelease=$(prop prerelease)

echo "version=$nbtversion" >> $GITHUB_ENV
echo "prerelease=$prerelease" >> $GITHUB_ENV
