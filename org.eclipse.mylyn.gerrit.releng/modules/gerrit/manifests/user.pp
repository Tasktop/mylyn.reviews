define gerrit::user(
	$username,
	$userid,
	$useremail,
  $usergroup,
  $userkey,
	$envid,
	$base = "/home/tools/gerrit",
) { 
	$envbase = "$base/$envid"
  
  file { "$envbase/adduser.${username}.sql":
    content => template('gerrit/adduser.sql.erb'),
    owner   => "$gerrit::userOwner",
    group   => "$gerrit::userGroup",
    require => File["$envbase"],
  }

  exec { "add user $username for $envid":
    command => "java -jar bin/gerrit.war gsql < $envbase/adduser.${username}.sql",
    cwd => "$envbase",
    user => "$gerrit::userOwner",
    require => [ File["$envbase/adduser.${username}.sql"], ],
  }

}
